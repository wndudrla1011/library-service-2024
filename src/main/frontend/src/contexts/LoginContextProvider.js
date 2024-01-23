import React, { useState, createContext } from 'react';

export const LoginContext = createContext();

function LoginContextProvider({ children }) {
  const [isLogin, setIsLogin] = useState(false);

  const logout = () => {
    setIsLogin(false);
  };

  return (
    <LoginContext.Provider value={{ isLogin, setIsLogin, logout }}>
      {children}
    </LoginContext.Provider>
  );
}

export default LoginContextProvider;
