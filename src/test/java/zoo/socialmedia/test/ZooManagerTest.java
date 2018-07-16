package zoo.socialmedia.test;

import org.junit.Rule;
import org.junit.Test;
import zoo.socialmedia.manager.RelationshipHandler;
import zoo.socialmedia.manager.ZooKeeper;
import zoo.socialmedia.manager.ZooManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ZooManagerTest {

    @Test
    public void test_registerAndShowAnimals() {
        PrintStream save_out=System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ZooManager zooManager = new ZooManager();
        ZooKeeper zoo = new ZooKeeper();
        zooManager.registerAndShowAnimals(zoo);
        assertThat(out.toString(), containsString("Dog One's favorite food is Meat"));
        System.setOut(save_out);
    }

    @Test
    public void test_without_register_showDayActivity() {
        PrintStream save_out=System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ZooManager manager = new ZooManager();
        ZooKeeper zooKeeper = new ZooKeeper();
        manager.showDayActivity(zooKeeper, 2);
        assertThat(out.toString(), is(not(containsString("Dog One's favorite food is Meat"))));
        System.setOut(save_out);
    }

    @Test
    public void test_with_register_showDayActivity() {
        PrintStream save_out=System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ZooManager manager = new ZooManager();
        ZooKeeper zooKeeper = new ZooKeeper();
        manager.registerAndShowAnimals(zooKeeper);
        manager.showDayActivity(zooKeeper, 2);
        assertThat(out.toString(), containsString("Dog One's favorite food is Meat"));
        System.setOut(save_out);
    }

    @Test
    public void test_showAnimalsAndProperties() {
        PrintStream save_out = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ZooManager zooManager = new ZooManager();
        zooManager.showAnimalsAndProperties();
        assertThat(out.toString(), containsString("Dog One's favorite food is Meat"));
        System.setOut(save_out);
    }

    @Test
    public void test_perform_day_activity(){
        PrintStream save_out = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        ZooManager manager = new ZooManager();
        manager.performDayActivity(1);

        assertThat(out.toString(), containsString("Dog One's favorite food is Meat"));
        System.setOut(save_out);
    }
}
