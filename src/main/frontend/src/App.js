import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React, { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [hello, setHello] = useState([]);

  useEffect((data) => {
    axios
      .get("/api/hello")
      .then((response) => setHello(response.data))
      .catch((error) => console.log(error));
  }, []);

  return (
    <div>
      <p>{hello.id}</p>
      <p>{hello.name}</p>
      <p>{hello.loginId}</p>
      <p>{hello.passwod}</p>
      <p>{hello.email}</p>
      <p>{hello.role}</p>
    </div>
  );
}

export default App;
