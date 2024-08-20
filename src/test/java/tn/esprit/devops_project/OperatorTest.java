package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.devops_project.entities.Operator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperatorTest {

    private Operator operator;

    @BeforeEach
    public void setUp() {
        operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("John");
        operator.setLname("Doe");
        operator.setPassword("password123");
    }

    @Test
    public void testOperatorProperties() {
        assertEquals(1L, operator.getIdOperateur());
        assertEquals("John", operator.getFname());
        assertEquals("Doe", operator.getLname());
        assertEquals("password123", operator.getPassword());
    }
}