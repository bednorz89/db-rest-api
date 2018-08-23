package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task 1", "Task1 - description");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertNotEquals(taskDto, task);
        assertTrue(task.getId() == taskDto.getId() &&
                task.getTitle().equals(taskDto.getTitle()) &&
                task.getContent().equals(taskDto.getContent()));
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Task 1", "Task1 - description");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertNotEquals(taskDto, task);
        assertTrue(task.getId() == taskDto.getId() &&
                task.getTitle().equals(taskDto.getTitle()) &&
                task.getContent().equals(taskDto.getContent()));
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", "Task1 - description"));
        tasks.add(new Task(2L, "Task 2", "Task2 - description"));
        //When
        List<TaskDto> tasksDto = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasks.size(), tasksDto.size());
        assertEquals(tasksDto.get(0).getContent(), "Task1 - description");
        assertEquals(tasksDto.get(1).getTitle(), "Task 2");
    }
}
