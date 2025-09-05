package com.project.TaskSync.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.TaskSync.Entity.Tasks;
import com.project.TaskSync.Services.TasksService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TasksService tService;

    // Create new task
    @PostMapping
    public Tasks addTaskHandler(@RequestBody Tasks task) {
        return tService.addTask(task);
    }

    // Get all non-deleted tasks
    @GetMapping
    public List<Tasks> getAllTasksHandler() {
        return tService.getAllTasks();
    }

    // Get task by ID
    @GetMapping("/{id}")
    public Tasks getTaskByIdHandler(@PathVariable UUID id) {
        return tService.getTaskById(id);
    }

    // Update task
    @PutMapping("/{id}")
    public Tasks updateTaskHandler(@PathVariable UUID id, @RequestBody Tasks task) {
        return tService.updateTask(id, task);
    }

    // Soft delete task
    @DeleteMapping("/{id}")
    public void deleteTaskHandler(@PathVariable UUID id) {
        tService.softDeleteTask(id);
    }

    // --- 🔄 Sync-related endpoints ---

    // Queue a task for sync
    @PostMapping("/{id}/queue-sync")
    public void queueTaskForSyncHandler(@PathVariable UUID id) {
        Tasks task = tService.getTaskById(id);
        tService.queueTaskForSync(task);
    }

    // Queue a task for delete (marks as deleted + pending sync)
    @PostMapping("/{id}/queue-delete")
    public void queueTaskForDeleteHandler(@PathVariable UUID id) {
        tService.queueTaskForDelete(id);
    }

    // Process sync queue (simulate sync with server)
    @PostMapping("/process-sync")
    public void processSyncQueueHandler() {
        tService.processSyncQueue();
    }
}
