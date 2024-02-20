import React, { useState, createContext } from 'react';
import api from '../apis/api';
import Cookies from 'js-cookie';
import * as auth from '../apis/auth';

export const LoginContext = createContext();

/**
 * 로그인
 * ✔ 로그인 체크
 * ✔ 로그인 요청
 * ✔ 로그아웃 요청
 *
 * 로그인 세팅 (로그인이 되었을 때 state를 어떻게 세팅할지)
 * 로그아웃 세팅 (로그아웃 되었을 때 state를 어떻게 세팅할지)
 */
function LoginContextProvider({ children }) {
  /*
     상태
     - 로그인 여부
     - 유저 정보
     - 권한 정보
     - 아이디 저장
   */
  //로그인 여부
  const [isLogin, setIsLogin] = useState(false);

  //유저 정보
  const [userInfo, setUserInfo] = useState({});

  //권한 정보
  const [roles, setRoles] = useState({ isUser: false, isAdmin: false });

  //아이디 저장
  const [rememberUserId, setRememberUserId] = useState();
  /* ------------------------------[state]--------------------------------- */

  /*
    로그인 체크
    - 쿠키에 jwt가 있는지 확인
    - jwt로 사용자 정보를 요청
  */
  const loginCheck = async () => {
    let response;
    let data;

    response = await auth.info();
  };

  //로그인
  const login = async (loginId, password) => {
    console.log(`loginId : ${loginId}`);
    console.log(`password : ${password}`);

    const response = await auth.login(loginId, password);
    const data = response.data;
    const status = response.status;
    const headers = response.headers;
    const authorization = headers.authorization;
    const accessToken = authorization.replace('Bearer ', ''); //JWT

    console.log(`data : ${data}`);
    console.log(`status : ${status}`);
    console.log(`headers : ${headers}`);
    console.log(`jwt : ${accessToken}`);

    //로그인 성공
    if (status === 200) {
      //로그인 체크 (/admin/members/${memberId}  <---  userData)
      alert('로그인 되었습니다.');
    }
  };

  //로그인 세팅
  // userData, accessToken (jwt)
  const loginSetting = (userData, accessToken) => {
    const [memberId, loginId, authList] = userData;
    const roleList = authList.map((auth) => auth.auth);

    console.log(`no : ${memberId}`);
    console.log(`loginId : ${loginId}`);
    console.log(`authList : ${authList}`);
    console.log(`roleList : ${roleList}`);

    //axios 객체의 header(Authorization : `Bearer ${accessToekn}`)
    api.defaults.headers.common.Authorization = `Bearer ${accessToken}`;

    //쿠키에 accessToken(jwt) 저장
    Cookies.set('accessToken', accessToken);

    //로그인 여부 : true
    setIsLogin(true);

    //유저정보 세팅
    const updateUserInfo = { memberId, loginId, roleList };
    setUserInfo(updateUserInfo);

    //권한정보 세팅
    const updatedRoles = { isUser: false, isAdmin: false };
    roleList.foreach((role) => {
      if (role === 'ROLE_USER' || role === 'ROLE_GUEST') {
        updatedRoles.isUser = true;
      }
      if (role === 'ROLE_STAFF' || role === 'ROLE_ADMIN') {
        updatedRoles.isAdmin = true;
      }
    });

    setRoles(updatedRoles);
  };

  //로그아웃 세팅
  const logoutSetting = () => {
    //axios 헤더 초기화
    api.defaults.headers.common.Authorization = undefined;

    //쿠키 초기화
    Cookies.remove('accessToken');

    //로그인 여부 : false
    setIsLogin(false);

    //유저 정보 초기화
    setUserInfo(null);

    //권한 정보 초기화
    setRoles(null);
  };

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
