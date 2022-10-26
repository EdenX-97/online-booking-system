package net.obs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Component
public class UpdateAgencyService implements ServletContextAware {

    @Autowired
    AgencyService agencyService;

    @Override
    public void setServletContext(ServletContext servletContext) {
        //agencyService.updateAgency();
    }
}
