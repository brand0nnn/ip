# Dino User Guide

Dino is a Command Line Interface (CLI) application that helps to keep track and manage tasks.

## Table of Contents
1. [Getting Started](#getting-started)
2. [Quick Reference](#quick-reference)
3. [Features](#features)
4. [Usage](#usage)

## Getting Started
1. Ensure that you have Java 11 installed on your computer. This application does not support newer versions of Java.
2. Download the .jar file from here.
3. Open a terminal in the directory that you saved the .jar file in, and run the following command:
```
java -jar ip.jar
```
## Quick Reference

|Command                                    |Parameters                             |
|-------------------------------------------|---------------------------------------|
|[`list`](#printing-task-list-list)         |None                                   |
|[`todo`](#adding-to-dos-todo)              |`<task name>`                          |
|[`deadline`](#adding-deadlines-deadline)   |`<task name> /by <date>`               |
|[`event`](#adding-events-event)            |`<task name> /from <start> /to <end>`  |
|[`mark`](#mark-as-complete-mark)           |`<index>`                              |
|[`unmark`](#unmark-completion-unmark)      |`<index>`                              |
|[`delete`](#deleting-a-task-delete)        |`<index>`                              |
|[`find`](#finding-a-task-find)             |`<keyword>`                            |
|[`bye`](#exiting-the-application-bye)      |None                                   |

## Features

### Add Tasks

Dino allows for the following types of tasks:
- To Do
- Deadline (Consists of due date)
- Event (Consists of start date and end date)

### Various Commands

Dino consists of many commands which allows users to manipulate their to-do list.
Commands are explained [below](#Usage).

### Local Storage

Dino saves past data locally, allowing users to access past data even after restarting the application.
Data is saved to `./data/savefile.txt` in the same directory as the `ip.jar` file.

## Usage

### Printing Task List: `list`

Prints the list of tasks.

Format: `list`

Expected outcome:
The current list of tasks is printed in the order that it is added.
Each task is shown in the following format:
- S/N
- Task Type
- Completed Status
- Description
- Start Data (for events)
- End Date (for deadline and events respectively)

Expected output:
```
1.[T][X] read book
2.[T][X] do survey
3.[D][ ] complete assignment (by: Mar 15 2025)
4.[E][ ] project meeting (from: Mar 15 2024 12:00 to: Mar 15 2025 14:00)
```

### Exiting the application: `bye`

Shuts down the application

Format: `bye`

Expected outcome:
The program saves all tasks in the task list into a save file, then exits after printing ASCII representation of Dino

### Adding To-Dos: `todo`

Create a to do task and add it to the list.

Format: `todo <task description>`

Example of usage: `todo read book`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

### Adding Deadlines: `deadline`

Create a task with a deadline and add it to the list.
Deadline is to be in the format YYYY-MM-DD.

Format: `deadline <task description> /by <deadline>`

Example of usage: `deadline complete assignment /by 2025-03-15`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
```
Got it. I've added this task:
  [D][ ] comeplete assignment
Now you have 2 tasks in the list.
```

### Adding Events: `event`

Create an event with a start date and end date and add it to the list.
Start and end dates are to be in the format YYYY-MM-DD HH:MM.

Format: `event <task description> /from <start date> /to <end date>`

Example of usage: `event project meeting /from 2025-03-15 12:00 /to 2025-03-15 14:00`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
```
Got it. I've added this task:
  [E][ ] project meeting
Now you have 3 tasks in the list.
```

### Mark as complete: `mark`

Mark a task, based on its index in the list, as completed.
When printing, this is denoted as an X.

Format: `mark <index>`

Example of usage: `mark 1`

Expected outcome:
The following message indicates that the task has successfully been marked as complete.
```
Nice! I've marked this task as done:
  [X] read book
```

### Unmark completion: `unmark`

Mark a task, based on its index in the list, as incomplete.
When printing, this is denoted as a blank space.

Format: `unmark <index>`

Example of usage: `unmark 1`

Expected outcome:
The following message indicates that the task has successfully been marked as incomplete.
```
OK, I've marked this task as not done yet:
  [ ] read book
```

### Deleting a Task: `delete`

Removes a task, based on its index in the list, from the list.

Format: `delete <index>`

Example of usage: `delete 1`

Expected outcome:
The following message indicates that the task has successfully been removed.
```
Got it. I've removed this task:
  [T][ ] read book
Now you have 3 tasks in the list.
```

### Finding a task: `find`

Searches for tasks with description containing or matching the input expression.

Format: `find <expression>`

Example if usage: `find book`

Expected outcome:
Prints a list of the tasks which contains or matches the input expression, in order of adding into the list.
```
Here are the matching tasks in your list:
1.[T][ ] read book
```
