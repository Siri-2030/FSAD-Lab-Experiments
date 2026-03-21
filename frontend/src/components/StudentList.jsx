import React, { useEffect, useState } from "react";

function StudentList({ refresh }) {
  const [students, setStudents] = useState([]);

  const fetchStudents = async () => {
    const res = await fetch("http://localhost:8080/students");
    const data = await res.json();
    setStudents(data);
  };

  useEffect(() => {
    fetchStudents();
  }, [refresh]); // 🔥 re-run when refresh changes

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Student List</h2>

      <table border="1" align="center">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
          </tr>
        </thead>
        <tbody>
          {students.map((s) => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{s.name}</td>
              <td>{s.email}</td>
              <td>{s.course}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default StudentList;