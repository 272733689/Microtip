package com.microtip.mqtt;

import com.microtip.util.PublishSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

/**\
 * 订阅者
 */
@Controller
public class Subscribe implements ApplicationListener<ContextRefreshedEvent> {
//    public static void main(String[] args) {
//        PublishSubscribe.subscribe();
//    }
//    @RequestMapping("/sendMqtt")  http 方式调用
//    @ResponseBody

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    //        spring web项目下，可能会造成二次执行，因为此时系统会存在两个容器，一个是spring本身的root application context，
    //        另一个是servlet容器（作为spring容器的子容器，projectName-servlet context），此时，加以下限制条件规避：

        if(event.getApplicationContext().getParent()==null){
            PublishSubscribe.subscribe();
            System.out.println("成功订阅-------");
        }
    }


    @RequestMapping("/planOutside")
    @ResponseBody
    public void onApplicationEvent() {
        System.out.println("任务计划的跳转");
    }
}
