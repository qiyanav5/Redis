package cn.jedis.test;

import cn.jedis.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    /*
        string 数据结构操作
    */
    @Test
    public void test(){
        //获取连接
        Jedis jedis=new Jedis("localhost",6379);//如果使用空参，默认值就是localhost，6379端口
        //操作
        //存储
        jedis.set("username","张三");
        //获取
        String username = jedis.get("username");
        System.out.println(username);

        //可以使用setex()方法存储可以指定过期时间的key value
        jedis.setex("activecode",20,"hehe");//将activecode:hehe键值对存入redis，并且20秒后自动删除该键值对
        //关闭连接
        jedis.close();
    }

    /*
        hash 数据结构操作
    */
    @Test
    public void test2(){
        //获取连接
        Jedis jedis=new Jedis("localhost",6379);//如果使用空参，默认值就是localhost，6379端口
        //操作
        //存储
        jedis.hset("user","name","李四");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","男");
        //获取
        String name = jedis.hget("user", "name");
        System.out.println(name);
        //获取hash中所有map中的数据
        Map<String, String> user = jedis.hgetAll("user");
        for (String s : user.keySet()) {
            System.out.println(s+":"+user.get(s));
        }

        //关闭连接
        jedis.close();
    }

    /*
        list 数据结构操作
    */
    @Test
    public void test3(){
        //获取连接
        Jedis jedis=new Jedis("localhost",6379);//如果使用空参，默认值就是localhost，6379端口
        //操作
        //存储
        jedis.lpush("mylist","a","b","c");//从左边存
        jedis.rpush("mylist","a","b","c");//从右边存

        //获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);//获取所有
        System.out.println(mylist);
        //list弹出
        String element = jedis.lpop("mylist");
        System.out.println(element);
        String element2 = jedis.rpop("mylist");
        System.out.println(element2);


        //关闭连接
        jedis.close();
    }

    /*
        set 数据结构操作
    */
    @Test
    public void test4(){
        //获取连接
        Jedis jedis=new Jedis("localhost",6379);//如果使用空参，默认值就是localhost，6379端口
        //操作
        //存储
        jedis.sadd("myset","java","php","c++");
        //获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        //关闭连接
        jedis.close();
    }

    /*
        sortedset 数据结构操作
    */
    @Test
    public void test5(){
        //获取连接
        Jedis jedis=new Jedis("localhost",6379);//如果使用空参，默认值就是localhost，6379端口
        //操作
        //存储
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",4,"妲己");
        jedis.zadd("mysortedset",2,"亚索");
        //获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);

        //关闭连接
        jedis.close();
    }

    /*
        jedis连接池
    */
    @Test
    public void test6(){
        //创建配置对象
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(50);//最大允许连接数
        config.setMaxIdle(10);//最大空闲连接

        //创建jedis连接池对象
        JedisPool jedisPool=new JedisPool(config,"localhost",6379);
        //获取连接
        Jedis jedis = jedisPool.getResource();
        //使用
        jedis.set("hehe","hehe");
        //关闭(归还到连接池)
        jedis.close();
    }

    /*
        jedis连接池工具类使用
    */
    @Test
    public void test7(){
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("hello","world");
        jedis.close();
    }
}
