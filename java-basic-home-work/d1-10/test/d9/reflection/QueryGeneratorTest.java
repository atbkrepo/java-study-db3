package d9.reflection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryGeneratorTest {

    QueryGenerator queryGenerator = new QueryGenerator();

    @Test
    public void testGetAll() {
        String expectedSql = "SELECT id, person_name, salary FROM person;";

        String recivedSql = queryGenerator.getAll(Person.class);
        assertEquals(expectedSql, recivedSql);
    }

    @Test
    public void testInsert() throws IllegalAccessException {
        Person person = new Person(1,"Vasia Pupkin",256.56);
        String expectedSql = String.format("INSERT INTO person(id, person_name, salary) VALUES (%d, \"%s\", %.2f);",person.getId(),person.getName(),person.getSalary());

        String recivedSql = queryGenerator.insert(person);
        assertEquals(expectedSql, recivedSql);
    }

    @Test
    public void testUpdate() throws IllegalAccessException {
        Person person = new Person(1,"Vasia Pupkin2",300.21);
        String expectedSql = String.format("UPDATE person" +
                " SET person_name = \"%s\", salary = %.2f" +
                " WHERE id = %d;",person.getName(),person.getSalary(),person.getId());

        String recivedSql = queryGenerator.update(person);
        assertEquals(expectedSql, recivedSql);
    }

    @Test
    public void testGetById() {
        int personId = 1;
        String expectedSql = String.format("SELECT id, person_name, salary FROM person WHERE id = %d;",personId);

        String recivedSql = queryGenerator.getById(Person.class, personId);
        assertEquals(expectedSql, recivedSql);
    }

    @Test
    public void testDelete() {
        int personId = 1;
        String expectedSql = String.format("DELETE FROM person WHERE id = %d;",personId);;

        String recivedSql = queryGenerator.delete(Person.class, personId);
        assertEquals(expectedSql, recivedSql);
    }
}