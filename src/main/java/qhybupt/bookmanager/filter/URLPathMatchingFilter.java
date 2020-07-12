package qhybupt.bookmanager.filter;

import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import qhybupt.bookmanager.service.PermissionService;
import qhybupt.bookmanager.util.SpringContextUtils;
 
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    PermissionService permissionService;
 
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
    	if(null==permissionService)
            permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);
    	
        String requestURI = getPathWithinApplication(request);
        
        if (requestURI.startsWith("/books")) {
//        	for (int i = requestURI.length()-1; i >= 0 ;i--) {
//        		if (requestURI.charAt(i) == '/') {
//        			requestURI = requestURI.substring(i+1);
//        		}
//        	}
        	String[] strs = requestURI.split("/");
        	requestURI = "/" + strs[strs.length - 1];
        }

        
        System.out.println("requestURI:" + requestURI);
        
        Subject subject = SecurityUtils.getSubject();
        // ���û�е�¼������ת����¼ҳ��
        if (!subject.isAuthenticated()) {
            WebUtils.issueRedirect(request, response, "/login");
            return false;
        }
 
        // �������·��Ȩ������û��ά�������û��ά����һ�ɷ���(Ҳ���Ը�Ϊһ�ɲ�����)
        boolean needInterceptor = permissionService.needInterceptor(requestURI);
        if (!needInterceptor) {
            return true;
        } else {
            boolean hasPermission = false;
            String userName = subject.getPrincipal().toString();
            Set<String> permissionUrls = permissionService.listPermissionURLs(userName);
            for (String url : permissionUrls) {
                // ��ͱ�ʾ��ǰ�û������Ȩ��
                if (url.equals(requestURI)) {
                    hasPermission = true;
                    break;
                }
            }
 
            if (hasPermission)
                return true;
            else {
                UnauthorizedException ex = new UnauthorizedException("��ǰ�û�û�з���·�� " + requestURI + " ��Ȩ��");
 
                subject.getSession().setAttribute("ex", ex);
 
                WebUtils.issueRedirect(request, response, "/unauthorized");
                return false;
            }
 
        }
 
    }
}
