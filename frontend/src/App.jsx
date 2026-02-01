import { useEffect, useState } from "react";
import "./App.css";

const CATEGORIES = [
  "Food",
  "Transport",
  "Rent",
  "Shopping",
  "Health",
  "Entertainment",
  "Utilities",
  "Other",
];

const TITLES = {
  Food: ["Groceries", "Restaurant", "Coffee"],
  Transport: ["Fuel", "Taxi", "Bus"],
  Rent: ["House Rent"],
  Shopping: ["Clothes", "Electronics"],
  Health: ["Doctor", "Medicine"],
  Entertainment: ["Movie", "Subscription"],
  Utilities: ["Electricity", "Internet"],
  Other: ["Misc"],
};

export default function App() {
  const [summary, setSummary] = useState(null);
  const [expenses, setExpenses] = useState([]);

  const [year] = useState(2026);
  const [month, setMonth] = useState(1);

  const [budget, setBudget] = useState("");

  const [category, setCategory] = useState("Food");
  const [title, setTitle] = useState("");
  const [amount, setAmount] = useState("");
  const [date, setDate] = useState(new Date().toISOString().slice(0, 10));

  const loadData = () => {
    fetch(`http://localhost:8081/api/summary/monthly/${year}/${month}`)
      .then((r) => r.json())
      .then((d) => {
        setSummary(d);
        setBudget(d.budget || "");
      });

    fetch("http://localhost:8081/api/expenses")
      .then((r) => r.json())
      .then(setExpenses);
  };

  useEffect(loadData, [month]);

  const saveBudget = () => {
    fetch(`http://localhost:8081/api/budgets/monthly/${year}/${month}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ limitAmount: Number(budget) }),
    }).then(loadData);
  };

  const addExpense = (e) => {
    e.preventDefault();
    fetch("http://localhost:8081/api/expenses", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        date,
        title,
        amount: Number(amount),
        category,
        note: "",
      }),
    }).then(() => {
      setTitle("");
      setAmount("");
      loadData();
    });
  };

  const deleteExpense = (id) => {
    fetch(`http://localhost:8081/api/expenses/${id}`, { method: "DELETE" }).then(loadData);
  };

  return (
    <div className="app">
      <div className="container">
        {/* HEADER */}
        <div className="header">
          <div>
            <h1 className="logo">
  <span>Spend</span>
  <span className="logo-accent">wise</span>
</h1>
            <p className="subtitle">Personal expense tracker</p>
          </div>

          <div className="month-picker">
            <label>Month</label>
            <select
              className="input"
              value={month}
              onChange={(e) => setMonth(Number(e.target.value))}
            >
              {[1,2,3,4,5,6,7,8,9,10,11,12].map((m) => (
                <option key={m} value={m}>
                  Month {m}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* GRID */}
        <div className="grid">
          {/* SUMMARY */}
          <div className="card">
            <h3>Monthly Overview</h3>

            {summary && (
              <>
                <div className="metric">
                  <span>Spent</span>
                  <b>₹{summary.spent}</b>
                </div>

                <div className="metric">
                  <span>Budget</span>
                  <b>₹{summary.budget}</b>
                </div>

                <div className="metric">
                  <span>Remaining</span>
                  <b>₹{summary.remaining}</b>
                </div>

                <input
                  className="input"
                  type="number"
                  placeholder="Set monthly budget"
                  value={budget}
                  onChange={(e) => setBudget(e.target.value)}
                />

                <div className="budget-row">
                  <button className="btn-outline" onClick={saveBudget}>
                    Save Budget
                  </button>

                  <div className={`status ${summary.overBudget ? "bad" : "ok"}`}>
                    {summary.overBudget ? "Over Budget" : "Within Budget"}
                  </div>
                </div>
              </>
            )}
          </div>

          {/* ADD EXPENSE */}
          <div className="card">
            <h3>Add Expense</h3>

            <form onSubmit={addExpense}>
              <select
                className="input"
                value={category}
                onChange={(e) => {
                  setCategory(e.target.value);
                  setTitle("");
                }}
              >
                {CATEGORIES.map((c) => (
                  <option key={c} value={c}>
                    {c}
                  </option>
                ))}
              </select>

              <input
                className="input"
                list="titles"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                placeholder="Expense title"
                required
              />

              <datalist id="titles">
                {TITLES[category].map((t) => (
                  <option key={t} value={t} />
                ))}
              </datalist>

              <input
                className="input"
                type="number"
                placeholder="Amount"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                required
              />

              <input
                className="input"
                type="date"
                value={date}
                onChange={(e) => setDate(e.target.value)}
              />

              <button className="btn-primary">Add Expense</button>
            </form>
          </div>
        </div>

        {/* LIST */}
        <div className="card table-card">
          <h3>Expense List</h3>

          <table>
            <thead>
              <tr>
                <th>Date</th>
                <th>Title</th>
                <th>Category</th>
                <th>Amount</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {expenses.map((e) => (
                <tr key={e.id}>
                  <td>{e.date}</td>
                  <td>{e.title}</td>
                  <td>{e.category}</td>
                  <td>₹{e.amount}</td>
                  <td>
                    <button className="delete-btn" onClick={() => deleteExpense(e.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
              {expenses.length === 0 && (
                <tr>
                  <td colSpan="5" className="empty">
                    No expenses yet.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
