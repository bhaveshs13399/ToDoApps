package com.course.mindorks.todoapp.clicklisteners

import com.course.mindorks.todoapp.model.Notes

interface ItemClickListeners {
    fun onClick(notes: Notes?)
}