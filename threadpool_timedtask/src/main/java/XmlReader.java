package main.java;

/**
 * Created by 110 on 2016/7/8.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XmlReader {


    public static void main(String[] args) {

        XmlReader.getTasks();
    }

    public static List getTasks() {

        List tasks = new ArrayList();

        System.out.println("load task config start...");

        String path = "/work/TaskManager/conf/taskconfig.xml";
        File file = new File(path);

        if (file.exists() && !file.isDirectory()) {

            try {
                SAXBuilder sx = new SAXBuilder();
                Document doc = sx.build(file);
                Element rootelement = doc.getRootElement();


                List<Element> childs = rootelement.getChildren();
                for (int i = 0; i < childs.size(); i++) {
                    TaskModel tModel = new TaskModel();
                    tModel.setClassName(childs.get(i).getChildText("class"));
                    System.out.println(childs.get(i).getChildText("class"));
                    tModel.setMethodName(childs.get(i).getChildText("method"));
                    System.out.println(childs.get(i).getChildText("method"));

                    String initialDelay = childs.get(i).getChildText("initialDelay");

                    tModel.setInitialDelay((Long.valueOf(initialDelay)));
                    System.out.println("距离首次运行还差" + initialDelay + "秒！");
                    tModel.setPeriod(Integer.valueOf(childs.get(i).getChildText("period")));
                    System.out.println(childs.get(i).getChildText("period"));
                    tasks.add(tModel);

                }
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JDOMException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            System.out.println("file no exist!");

        }
        System.out.println("load task config end !");
        return tasks;

    }

}
