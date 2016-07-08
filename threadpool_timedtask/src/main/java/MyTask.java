package main.java;

/**
 * Created by 110 on 2016/7/8.
 */
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class MyTask implements Runnable {

    private TaskModel taskModel;
    public MyTask() {}

    public MyTask(TaskModel tm) {
        this.taskModel = tm;
    }

    public void run() {
        System.out.println("call at " + (new Date()));
        try {
            Class<?> classType = Class.forName(taskModel.getClassName());
            Method getMethod = classType.getMethod(taskModel.getMethodName());
            getMethod.invoke(classType);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
