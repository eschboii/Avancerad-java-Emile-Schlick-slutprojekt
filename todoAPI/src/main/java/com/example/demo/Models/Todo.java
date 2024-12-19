package com.example.demo.Models;

public class Todo {

        private int id;
        private String task;
        private String description;

        public Todo(int id, String task, String description) {
            this.id = id;
            this.task = task;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String author) {
            this.description = description;
        }
}
