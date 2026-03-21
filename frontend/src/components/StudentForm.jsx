import React, { useState } from "react";

function StudentForm({ setRefresh }) {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: ""
  });

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    await fetch("http://localhost:8080/students", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(student)
    });

    alert("Student Added!");

    // 🔥 refresh table
    setRefresh(prev => prev + 1);

    // 🔥 clear form
    setStudent({ name: "", email: "", course: "" });
  };

  return (
    <div style={{ textAlign: "center", marginBottom: "20px" }}>
      <h2>Add Student</h2>

      <form onSubmit={handleSubmit}>
        <input name="name" placeholder="Name" value={student.name} onChange={handleChange} />
        <br /><br />

        <input name="email" placeholder="Email" value={student.email} onChange={handleChange} />
        <br /><br />

        <input name="course" placeholder="Course" value={student.course} onChange={handleChange} />
        <br /><br />

        <button type="submit">Add Student</button>
      </form>
    </div>
  );
}

export default StudentForm;