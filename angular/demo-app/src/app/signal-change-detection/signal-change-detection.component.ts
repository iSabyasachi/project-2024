import { Component, computed, effect, signal, ViewChild } from '@angular/core';
import { Task, TasksFilter } from '../models/topic.model';
import { SnackbarComponent } from '../snackbar/snackbar.component';

@Component({
  selector: "app-signal-change-detection",
  templateUrl: './signal-change-detection.component.html',
  styleUrl: './signal-change-detection.component.scss'
})
export class SignalChangeDetectionComponent {
  @ViewChild(SnackbarComponent) snackbar!: SnackbarComponent;
  
  tasks = signal<Task[]>([
    {title: 'Background', priority: false, completed: false},
    {title: 'Benefits', priority: false, completed: false},
    {title: 'Performance', priority: true, completed: false},
    {title: 'Best practices', priority: false, completed: false},
  ]);

  finishedTasksCount = computed(() => {
    return this.tasks().filter(task => task.completed).length;
  });

  filter = signal(TasksFilter.All);
  filters = TasksFilter;
  filteredTasks = computed(() => {
    switch(this.filter()) {
      case TasksFilter.All: return this.tasks();
      case TasksFilter.Priority: return this.tasks().filter(taskItem => taskItem.priority); 
      case TasksFilter.Active: return this.tasks().filter(taskItem => !taskItem.completed); 
      case TasksFilter.Completed: return this.tasks().filter(taskItem => taskItem.completed); 
    }
  }); 

  changeFilter(filter: TasksFilter){
    this.filter.set(filter);
  }

  /**
   * implement the ability to toggle a task as completed or incomplete
   * @param task 
   */
  toggleTask(task: Task){
    const updatedTasks = this.tasks().map(taskItem => 
      taskItem.title === task.title ? 
      {...taskItem,
        completed: !taskItem.completed
      } : taskItem
    );
    this.tasks.set(updatedTasks);
  }

  addTask(titleInput: HTMLInputElement, priorityInput: HTMLInputElement){
    if(titleInput.value){
      const newTask = {
        title: titleInput.value,
        completed: false,
        priority: priorityInput.checked
      }
      this.tasks.set([...this.tasks(), newTask]);
    }
  }

  completedEffectRef = effect(() => {
    const tasks = this.tasks();
    if(this.finishedTasksCount() === tasks.length && tasks.length > 0) {
      this.snackbar.show();
    }
  });


}