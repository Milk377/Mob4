# 모바일프로젝트 팀플
모바일 프로젝트 팀플 repository
아래순서 참고하시면 좋습니다.

## Fork, clone 진행 
메인 저장소에 바로 push할 권한이 주어졌지만, 안전하게 하기 위해 자신의 repository에 fork 하고 자신의 로컬 컴퓨터에 clone 

#### 1. 우측상단 fork버튼 
#### 2. 우측상단 내 아이콘 클릭 -> Your repositorys -> fork해온 저장소 들어가서 주소 복사 -> clone하고자하는 로컬 컴퓨터의 장소에 들어가서 git bash 실행
#### 3. clone 코드 입력
```
git clone <clone해서 내 레포지토리에생긴 저장소 주소>
```
#### 4. upstream 저장소 설정
```
git remote add upstream <clone해서 내 레포지토리에생긴 저장소 주소>
```
#### 5. remote 저장소 확인 (origin과 upstream이 같아야)
```
git remote
```
#### 6. 내 로컬컴퓨터에서 branch로 pull request 연습도 해보기
```
git checkout -b sungrae (sungrae는 각자 이름으로)
```
#### 7. branch 확인
```
git branch
```
#### 8. 작업 진행후 Add, commit, push 하기
```
git add -A			(-A는 변경사항 모두 add한다는거)
git commit -m "메시지공간"	    (" " 는 메시지를 적는곳)
git push origin sungrae		(sungrae는 각자 만든 branch명 적기)
```
#### 9. 메인이 아닌 fork해온 자신의 저장소에서 스스로가 스스로에게 pull request하기
push가 진행이 잘되었으면 git에 접속시 본인계정의 git repository에
```
Compare & pull request  	(버튼이 활성화)
```




	
->git remote add upstream <fork해온 내 레포지토리의 주소> -> git remote -v 로 저장소 목록 확인
-> master에 바로 push 하기 이전에 자신의 로컬 컴퓨터에서 테스트를 해보는게 좋기 때문에 branch 생성
-> git checkout -b sungrae     (sungrae는 각자 이름으로)
-> git branch로 브랜치 확인

