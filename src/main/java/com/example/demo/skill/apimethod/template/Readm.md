



1.TemplateService接口放到core中作为依赖

2.AbstractTemplateServiceImpl也可以放到core中

3.TemplateServiceImpl对外暴露服务，微服务，服务提供方provider

4.TemplateController中通过指定url或者名称来找到对应的bean,服务消费方consumer