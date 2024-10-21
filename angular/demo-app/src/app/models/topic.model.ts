export type Task = {
    title: string;
    completed: boolean;
    priority: boolean;
}

export enum TasksFilter {
    All,
    Active,
    Completed,
    Priority
}