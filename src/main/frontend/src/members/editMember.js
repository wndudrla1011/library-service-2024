import React from "react";

function editMember() {
  return (
    <div>
      <div>
        <h2>회원 정보 수정</h2>
      </div>

      <form>
        <input />
        <div>
          <p>전체 오류 메시지</p>
        </div>

        <div>
          <label>회원번호</label>
          <input />
        </div>

        <div>
          <label>회원 ID</label>
          <input />
          <div></div> {/* field error */}
        </div>

        <div>
          <label>비밀번호</label>
          <input />
          <div></div> {/* field error */}
        </div>

        <div>
          <label>회원 이름</label>
          <input />
          <div></div> {/* field error */}
        </div>

        <div>
          <label>이메일 주소</label>
          <input />
          <div></div> {/* field error */}
        </div>

        <div>
          <div>권한</div>
          <select>
            <option>권한 선택 🔻</option>
            <option></option>
          </select>
        </div>

        <hr />

        <div>
          <div>
            <button>수정완료</button>
          </div>

          <div>
            <button>취소</button> {/* common */}
          </div>

          <div>
            <button>취소</button> {/* !common */}
          </div>
        </div>
      </form>
    </div>
  );
}

export default editMember;
