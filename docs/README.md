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
|[`list`](#printing-todo-list-list)         |None                                   |
|[`todo`](#adding-to-dos-todo)              |`<task name>`                          |
|[`deadline`](#adding-deadlines-deadline)   |`<task name> /by <date>`               |
|[`event`](#adding-events-event)            |`<task name> /from <start> /to <end>`  |
|[`mark`](#mark-completion-mark)            |`<index>`                              |
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

