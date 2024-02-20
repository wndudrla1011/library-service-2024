import api from './api';

//로그인
export const login = (loginId, password) =>
  api.post(`/login?loginId=${loginId}&password=${password}`);

//사용자 정보
export const info = (memberId) => api.get(`/admin/members/${memberId}`);

//회원가입
export const join = (data) => api.post(`/members/add`, data);

//회원 정보 수정
export const update = (data, memberId) =>
  api.put(`/admin/members/${memberId}/edit`, data);

//회원 탈퇴
export const remove = (memberId) => api.delete(`/admin/members`);
