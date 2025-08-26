import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// order management system

class Task {
    private int userId;
    private int taskId;
    private int priority;

    public Task(int userId, int taskId, int priority) {
        this.userId = userId;
        this.taskId = taskId;
        this.priority = priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getUserId() {
        return userId;
    }
}

class TaskManager {

    private Map<Integer, Task> taskMap;
    PriorityQueue<Task> taskQueue;
    public TaskManager(List<List<Integer>> tasks) {
        taskQueue = new PriorityQueue<>(
                (a, b) -> {
                    if (b.getPriority() != a.getPriority()) {
                        return b.getPriority() - a.getPriority();
                    } else {
                        return b.getTaskId() - a.getTaskId();
                    }
                }
        );
        taskMap = new HashMap<>();
        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            taskMap.put(taskId, new Task(userId, taskId, priority));
            taskQueue.add(new Task(userId, taskId, priority));
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new Task(userId, taskId, priority));
        taskQueue.add(new Task(userId,taskId, priority));
    }

    public void edit(int taskId, int newPriority) {
        Task task = taskMap.get(taskId);
        if (task!=null) {
//            taskQueue.remove(task);
            task.setPriority(newPriority);
            taskQueue.add(new Task(task.getUserId(), task.getTaskId(), newPriority));
        }
    }

    public void rmv(int taskId) {
        Task task = taskMap.remove(taskId);
//        if (task!=null) {
//            taskQueue.remove(task);
//        }
    }

    public int execTop() {
        while(!taskQueue.isEmpty()) {
            Task taskFromQueue = taskQueue.poll();
            int taskId = taskFromQueue.getTaskId();

            if(taskMap.containsKey(taskId) && taskMap.get(taskId).getPriority()==taskFromQueue.getPriority()) {
                int userId = taskMap.get(taskId).getUserId();
                taskMap.remove(taskId);
                return userId;
            }
        }
        return -1;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */