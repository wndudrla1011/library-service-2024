import React from "react";

function memberInfo() {
  return (
    <div>
      <div>
        <h2>회원 정보</h2>
      </div>

      <div>
        <label>회원 ID</label>
        <input />
      </div>

      <div>
        <label>비밀번호</label>
        <input />
      </div>

      <div>
        <label>회원 이름</label>
        <input />
      </div>

      <div>
        <label>이메일 주소</label>
        <input />
      </div>

      <div>
        <label>등급</label>
        <input />
      </div>
      <div>
        <label>권한</label>
        <input />
      </div>

      <hr />

      <div>
        <div>
          <button>수정</button> {/* 모든 회원 -> 내 정보 수정 */}
        </div>
        <div>
          <button>수정</button> {/* 관리자 -> 회원 정보 수정 */}
        </div>
        <div>
          <button>취소</button> {/* 내 정보 -> 메인으로 */}
        </div>
        <div>
          <button>취소</button> {/* 회원 정보 -> 회원 목록 */}
        </div>
      </div>
    </div>
  );
}

export default memberInfo;
