## Application Flow & Functional Test Scenarios

This section describes how the application will be tested and what functional flows must be supported.

---

## 1. Application Startup Flow
- On application start:
    - Load previously saved task data using serialization (if available)
    - Initialize required collections and services
    - Display the main menu

---

## 2. Main Menu Flow
The application should display a menu similar to:

1. Create User
2. Create Task
3. Assign Task to User
4. View Tasks
5. Update Task
6. Delete Task
7. Sort / Filter Tasks
8. Save & Exit

The menu should repeat until the user chooses to exit.

---

## 3. User Creation Flow
- User selects **Create User**
- System asks for:
    - User name
    - User type (Admin or Regular)
- System creates the user and assigns a unique user ID
- Display success or error message

---

## 4. Task Creation Flow
- User selects **Create Task**
- System asks for:
    - Task title
    - Priority
- System:
    - Generates a unique task ID
    - Sets task status to `NEW`
    - Stores the task in the system
- Display success confirmation

---

## 5. Task Assignment Flow
- User selects **Assign Task**
- System asks for:
    - Task ID
    - User ID
- System:
    - Validates task and user existence
    - Assigns the task to the specified user
- Throw a custom exception if:
    - Task does not exist
    - User does not exist

---

## 6. View Tasks Flow
- User selects **View Tasks**
- System provides options:
    1. View all tasks
    2. View tasks by user
    3. View tasks by status
    4. View tasks by priority
- Use **Streams and Lambdas** for filtering
- Display tasks in a readable format

---

## 7. Update Task Flow
- User selects **Update Task**
- System asks for:
    - Task ID
    - Fields to update (status, priority, title)
- System:
    - Updates allowed fields
    - Uses Optional to handle missing tasks
- Throw a custom exception if task is not found

---

## 8. Delete Task Flow
- User selects **Delete Task**
- System asks for:
    - Task ID
- System:
    - Deletes the task if found
    - Displays confirmation message
- Only Admin users should be allowed to delete tasks

---

## 9. Sort & Filter Flow
- User selects **Sort / Filter Tasks**
- Sorting options:
    - Sort by Priority
    - Sort by Status
- Sorting must be implemented using:
    - Strategy Pattern
- Filtering must be implemented using:
    - Streams API

---

## 10. Role-Based Access Flow
- Admin User:
    - Can create, update, delete any task
- Regular User:
    - Can view tasks
    - Can update only their assigned tasks
- Invalid operations should throw a custom exception

---

## 11. Data Persistence Flow
- On selecting **Save & Exit**:
    - Serialize all task data to file
- On next application start:
    - Deserialize and restore data

---

## 12. Error Handling Flow
- Handle all invalid inputs gracefully
- Use custom exceptions for:
    - Task not found
    - Invalid user operation
- Application should not terminate unexpectedly

---

## 13. Reporting Flow (Optional)
- Generate a task summary report:
    - Total tasks
    - Tasks by status
    - Tasks by priority
- Use `StringBuilder` to generate report output

---

## 14. Non-Functional Requirements
- Code must follow clean code principles
- Meaningful class and method names
- Proper package structure
- Avoid duplicate code
- No hard-coded values

---

## Evaluation Focus
- Correct flow implementation
- Proper use of Java features
- Clean and readable code
- Exception handling
- Design pattern usage
