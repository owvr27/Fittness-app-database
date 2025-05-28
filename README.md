


# Fitness App Database

A MySQL-based backend for managing a fitness application that tracks users, workouts, goals, and progress.



📌 Features

- User account and profile management  
- Workout routines and categories  
- Exercise logs and history  
- Nutrition & goal tracking  
- Trainer & client linking (optional)

---

🧩 Database Structure

Tables:

1. Users  
   - id (PK), name, email, password, age, gender, height, weight

2. Workouts  
   - id (PK), user_id (FK), workout_date, duration, notes

3. Exercises  
   - id (PK), name, category (e.g., cardio, strength), description

4. Workout_Exercises  
   - id (PK), workout_id (FK), exercise_id (FK), sets, reps, weight

5. Goals  
   - id (PK), user_id (FK), type (e.g., weight loss), target_value, current_value, deadline

---

⚙ Technologies

- Database: MySQL  
- Tools: phpMyAdmin, MySQL Workbench, CLI  
- Backend integration (optional): Java, Python, or Node.js

---

🔍 How to Use

1. Import the fitness_app.sql file into your MySQL server.  
2. Connect your backend code to the database.  
3. Perform CRUD operations for users, workouts, and goals.

---

📁 File Structure
- fitness_app.sql → SQL file for creating and populating tables  
- README.md → Project description  
