package com.example.vipshah.espressodemo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExampleUnitTest {

    @Test
    public void testKidsFilterLogic() {
        Person person1 = new Person("Vipul", 27);
        Person person2 = new Person("Vinay", 10);
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);

        Assert.assertNotNull(PersonUtils.getKids(people));
        Assert.assertEquals(PersonUtils.getKids(people).size(), 1);
        Assert.assertNotNull(PersonUtils.getKids(people).get(0).name);
        Assert.assertEquals(PersonUtils.getKids(people).get(0).name, "Vinay");
    }

    @Test
    public void testNonNullEvenNumberFilterMethod() {
        Assert.assertNotNull(PersonUtils.getEvenNumbers(new Integer[]{1, 2, 3, 4}));
    }
}