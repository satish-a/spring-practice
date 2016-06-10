As part of learning Spring framework, I started making a **[simple invoice application](https://github.com/SatishAb/invsys)** using Spring Framework components, Spring MVC, Spring Security, Spring data and various other tools like Query DSL, Maven, Ext JS/Charts, Cloud foundry to implement and deploy the application. Application is posted to **[github](https://github.com/SatishAb/invsys)** for your reference.  
  
Sample Invoice application allows, to create users/customers/vendors, create products, create purchase orders, create sales orders, and allows to display basic charts of sales/purchases over a period of time.  

In this tutorial I like to explain different parts/tiers of the application. Following is the list of posts explaining different tiers of application structure.  
  
**[Configuring Sample Invoice Spring Application - Maven & Application Configuration](http://satishab.blogspot.com/2012/10/part-1-configuring-sample-invoice.html)**  
This post focuses on bootstrapping the spring application. Details about maven dependencies for the Spring Framework, Hibernate, Query DSL and other dependencies are discussed. And also explains about configuration details for Spring Framework, Spring MVC, Spring Data/Hibernate and Spring Security.  
  
**[Persistence Layer with Hibernate, Spring Data JPA and Query DSL](http://satishab.blogspot.com/2012/10/part-2-persistence-layer-with-hibernate.html)**  
This post talks about persistent details of application like, data model, JPA/Hibernate entities & relations, Spring Data JPA introduction & the Spring Data repositories and Query DSL querying usages.  
  
**[Web Layer using Spring MVC and Services](http://satishab.blogspot.com/2012/10/part-3-web-layer-with-spring-mvc-for.html)**  
This post describes application web layer and details about Spring MVC & various web controllers used in the application. Also describes the view technology used in the application, various spring framework annotations used like @service and the trace interceptor used for logging purposes.  
  
**[Securing web application with Spring Security ajax form based authentication](http://satishab.blogspot.com/2012/10/part-4-securing-web-application-with.html)**  
This post talks about configuring and securing the application using Spring Security with form based authentication. Also details about configuration details of Spring Security for supporting AJAX based login.  
  
**[ExtJS Forms, Grids & Charts for Web UI](http://satishab.blogspot.com/2012/10/part-5-extjs-4-forms-grids-charts-for.html)**  
This post describes about the application Web UI. JSP & EXTJS are used extensively to develop the Web UI. All the forms, grids, and various charts are implemented using EXT JS and all these details will be discussed in this post.  
  
**Deploying application to cloud foundry**  
Cloudfoundry by VmWare provides very easy way of deploying the application to the cloud.  
  
Cloudfoundry has an excellent article detailing about each and every step to create, deploy, install and operate on the services in cloud. Please check here for the [documentation](http://docs.cloudfoundry.com/tools/STS/deploying-CF-Eclipse.html). This post details the steps involved for deploying the application to cloud foundry, cloud service by VmWare. Also describes about the newly introduced tunnelling service for connecting to the database service defined in the cloud.  
  
**Wrapup:**  
This prototype application has enabled me in learning an end-to-end use case development of a web application using Spring technologies, integration of EXTJS with Spring MVC and finally deploying an application in cloud using Cloudfoundry.