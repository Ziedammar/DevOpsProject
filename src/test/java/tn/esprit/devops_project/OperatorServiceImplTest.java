package tn.esprit.devops_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OperatorServiceImplTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        List<Operator> mockOperators = new ArrayList<>();
        mockOperators.add(new Operator(1L, "John", "Doe", "password123"));
        mockOperators.add(new Operator(2L, "Jane", "Smith", "pass456"));

        Mockito.when(operatorRepository.findAll()).thenReturn(mockOperators);

        List<Operator> retrievedOperators = operatorService.retrieveAllOperators();
        assertEquals(2, retrievedOperators.size());
        assertEquals("John", retrievedOperators.get(0).getFname());
        assertEquals("Doe", retrievedOperators.get(0).getLname());
        assertEquals("password123", retrievedOperators.get(0).getPassword());
    }

    @Test
    public void testAddOperator() {
        Operator newOperator = new Operator(3L, "Alice", "Johnson", "newPassword");

        Mockito.when(operatorRepository.save(newOperator)).thenReturn(newOperator);

        Operator addedOperator = operatorService.addOperator(newOperator);
        assertEquals("Alice", addedOperator.getFname());
        assertEquals("Johnson", addedOperator.getLname());
        assertEquals("newPassword", addedOperator.getPassword());
    }

    @Test
    public void testDeleteOperator() {
        Long operatorId = 4L;
        Operator existingOperator = new Operator(operatorId, "Test", "User", "pass123");

        Mockito.when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(existingOperator));
        Mockito.doNothing().when(operatorRepository).deleteById(operatorId);

        operatorService.deleteOperator(operatorId);
        Mockito.verify(operatorRepository, Mockito.times(1)).deleteById(operatorId);
    }

    @Test
    public void testUpdateOperator() {
        Operator existingOperator = new Operator(5L, "John", "Doe", "password123");

        Mockito.when(operatorRepository.save(existingOperator)).thenReturn(existingOperator);

        Operator updatedOperator = operatorService.updateOperator(existingOperator);
        assertEquals("John", updatedOperator.getFname());
        assertEquals("Doe", updatedOperator.getLname());
        assertEquals("password123", updatedOperator.getPassword());
    }

    @Test
    public void testRetrieveOperator() {
        Long operatorId = 6L;
        Operator existingOperator = new Operator(operatorId, "Alice", "Johnson", "newPassword");

        Mockito.when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(existingOperator));

        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);
        assertEquals("Alice", retrievedOperator.getFname());
        assertEquals("Johnson", retrievedOperator.getLname());
        assertEquals("newPassword", retrievedOperator.getPassword());
    }
}