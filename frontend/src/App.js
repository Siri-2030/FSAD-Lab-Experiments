import React, { useEffect, useState } from "react";

function App() {

  const [message, setMessage] = useState("Connecting to backend...");

  useEffect(() => {
    fetch("http://localhost:8080/api/hello")
      .then((response) => response.text())
      .then((data) => setMessage(data))
      .catch(() => setMessage("Backend not connected"));
  }, []);

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h1 style={styles.title}>Full Stack Application</h1>
        <p style={styles.subtitle}>Spring Boot + React Deployment</p>

        <div style={styles.box}>
          <h2>{message}</h2>
        </div>

        <p style={styles.footer}>Frontend successfully connected to Backend</p>
      </div>
    </div>
  );
}

const styles = {
  container: {
    backgroundColor: "#f5f7fa",
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  },

  card: {
    backgroundColor: "white",
    padding: "40px",
    borderRadius: "12px",
    boxShadow: "0px 4px 15px rgba(0,0,0,0.1)",
    textAlign: "center",
    width: "400px",
  },

  title: {
    marginBottom: "10px",
  },

  subtitle: {
    color: "gray",
    marginBottom: "30px",
  },

  box: {
    backgroundColor: "#eaf2ff",
    padding: "20px",
    borderRadius: "8px",
    marginBottom: "20px",
  },

  footer: {
    fontSize: "14px",
    color: "gray",
  },
};

export default App;