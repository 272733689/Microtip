package com.microtip.util;

//import com.microtip.Mapper.Device_ListMapper;
//import com.microtip.Mapper.UserMapper;
//import com.microtip.entity.Device_List;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class PublishSubscribe {
//    @Autowired
//    private static com.microtip.Mapper.Device_ListMapper Device_ListMapper;


    //private static String serviceURI = "tcp://120.55.61.147:2883";  //测试
    private static String serviceURI = "tcp://mqtttest.ebike-charge.com:2883";
    private static String clientID = UUID.randomUUID().toString();
    private static MqttClientPersistence persistence = new MemoryPersistence();
    //如果mqtt服务配置了匿名访问，则不需要使用用户名和密码就可以实现消息的订阅和发布
    private static String username = "admin";
    private static String password = "Mqtt@2k19";
    private static String topic = "/b03b99VXEV1/5c7778354c1d6f180cc99202/device";
    private static String topic_device = "/b03b99VXEV1/5c7778354c1d6f180cc99202/device"; //订阅设备信息
    private static String topic_parameter = "/b03b99VXEV1/5c7778354c1d6f180cc99202/parameter"; //订阅线路详情

    private static String topic_power = "/b03b99VXEV1/5c7778354c1d6f180cc99202/power"; // 订阅功率
    private static String topic_status = "/b03b99VXEV1/5c7778354c1d6f180cc99202/status"; // 订阅开关状态
    private static String topic_energy = "/b03b99VXEV1/5c7778354c1d6f180cc99202/energy"; // 订阅电量
    private static String topic_temp = "/b03b99VXEV1/5c7778354c1d6f180cc99202/temp"; // 订阅温度
    private static String topic_message = "/b03b99VXEV1/5c7778354c1d6f180cc99202/message"; // 订阅故障告警信息




    //当访问页面时 创建session 对象。订阅主题 并把主题的参数放入session 中



    /*
        消息服务质量，一共有三个：
        0：尽力而为。消息可能会丢，但绝不会重复传输
        1：消息绝不会丢，但可能会重复传输
        2：恰好一次。每条消息肯定会被传输一次且仅传输一次
     */
    private static int qos = 0;

    /**
     * 消息发布
     *
     * @author wzq
     **/
    public static void publish() {
        try {
            MqttClient client = new MqttClient(serviceURI, clientID, persistence);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(username);
            connectOptions.setPassword(password.toCharArray());
            connectOptions.setCleanSession(false);
            //发布者连接服务
            client.connect(connectOptions);
            System.out.println("发布者连接状态： " + client.isConnected());
            MqttTopic mqttTopic = client.getTopic(topic);
            //MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            int i = 1;
            String message = "hello，智能公厕-";

            while (true) {
                String payLoad = message + i++;
                mqttMessage.setPayload(payLoad.getBytes());
                MqttDeliveryToken deliveryToken = mqttTopic.publish(mqttMessage);
                if (!deliveryToken.isComplete()) {
                    System.out.println("发布者发布消息： " + payLoad + " 失败");
                    deliveryToken.waitForCompletion();
                } else {
                    System.out.println("发布者发布消息： " + payLoad + " 成功");
                }
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息订阅
     *
     * @author wzq
     **/
    public static void subscribe() {
        try {
            MqttClient client = new MqttClient(serviceURI, clientID, persistence);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("订阅者连接丢失...");
                    System.out.println(cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("订阅者接收到消息： " + message.toString());
                    System.out.println("订阅者谁的消息： " + topic);
                    //修改成从数据中 判断 通道号 确定返回的是哪个主题
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });
            MqttConnectOptions connectOptions = new MqttConnectOptions();
//            connectOptions.setUserName(username);
//            connectOptions.setPassword(password.toCharArray());
            connectOptions.setCleanSession(false);
            //订阅者连接订阅主题
            client.connect(connectOptions);
            //根据数据库查询出来需要订阅的设备

//            List<Device_List> Device_List = Device_ListMapper.getDevice();
//
//            System.out.println("查询出来的 是"+Device_List);
//            System.out.println("查询出来的 是"+Device_List.get(0));
//            System.out.println("查询出来的 是"+Device_List.get(2));
//            client.subscribe(topic_device, qos);
//            client.subscribe(topic_power, qos);
            client.subscribe(topic_status, qos);

            System.out.println("订阅者连接状态： " + client.isConnected());
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }



}
