import React, { useState } from "react";
import LocalUserList from "./LocalUserList";
import UserList from "./UserList";
import FakePostList from "./FakePostList";
import "./Dashboard.css";

function Dashboard() {
  const [page, setPage] = useState("");

  return (
    <div className="dashboard">
      <h1 className="title">📊 React API Dashboard</h1>

      <div className="button-container">
        <button onClick={() => setPage("local")}>Local Users</button>
        <button onClick={() => setPage("api")}>Users API</button>
        <button onClick={() => setPage("fake")}>Fake API Posts</button>
      </div>

      <div className="content">
        {page === "local" && <LocalUserList />}
        {page === "api" && <UserList />}
        {page === "fake" && <FakePostList />}
      </div>
    </div>
  );
}

export default Dashboard;