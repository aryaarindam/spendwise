# Spendwise 
A clean, modern personal expense tracker with monthly budgeting — built with **Spring Boot + PostgreSQL + React**.

> Track expenses, set monthly budgets, and instantly see how much you’ve spent vs. what’s left.

---

## Features
- **Monthly summary dashboard** (Spent / Budget / Remaining)
- **Set & update monthly budget**
- **Add expenses** with category + smart title suggestions
- **Expense list** with delete support
- Month-wise tracking (budget + summary updates per selected month)

---

##  Tech Stack
**Backend**
- Java 17, Spring Boot
- Spring Web, Spring Data JPA, Validation
- PostgreSQL

**Frontend**
- React (Vite)
- Vanilla CSS (clean UI, recruiter-friendly)

---

##  Screenshots
> Add your screenshots in `docs/screenshots/`

### Dashboard
![Dashboard](docs/screenshots/dashboard.png)

### Add Expense
![Add Expense](docs/screenshots/add-expense.png)

### Expense List
![Expense List](docs/screenshots/expense-list.png)

---

## Prerequisites
Make sure you have:
- Java 17+
- Maven
- Node 20+
- PostgreSQL

---

## ⚙️ Setup (Run locally)

### 1) Backend (Spring Boot)

Go to backend folder:
cd spendwise-backend


Create DB in PostgreSQL:
CREATE DATABASE spendwise;

Run backend:
mvn spring-boot:run

Backend will run on:
http://localhost:8081

### 2) Frontend (React)

Go to frontend folder:
cd frontend

Install + run:
npm install
npm run dev

Frontend runs on:
http://localhost:5173


### 🔌 API Endpoints (v1)

GET /api/summary/monthly/{year}/{month}

PUT /api/budgets/monthly/{year}/{month}

GET /api/expenses

POST /api/expenses

DELETE /api/expenses/{id}
