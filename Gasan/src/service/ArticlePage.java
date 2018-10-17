package service;

import java.util.List;

import model.ArticleFoodApply;


//한 페이지에서 보여줄 게시글 정보와 페이지 관련 정보를 담는 클래스.
//페이지(게시글 목록의 한 화면)에 여기서 담겨있는 내용을 출력할 예정.
public class ArticlePage {

	// Article 객체를 하나만 보여줄 것이 아니므로 리스트에 담는다.
	private List<ArticleFoodApply> artList;// 게시글 정보를 담고 있는 객체들의 리스트.
	// 이 안에 들어있는 것:게시글id,제목,작성자,작성자id,작성일,수정일,조회수

	// 페이지 번호
	private int currentPage;// 사용자가 요청한 페이지 번호

	private int totalPages;// 전체 페이지 수(게시물의 숫자가 아니라 페이지의 수.)한 페이지에 10개씩 보여주는데 11개면 2개의 페이지가 됨.

	private int total;// 게시글의 전체 갯수

	private int startPage;// 화면 하단에 보여줄 페이지 링크의 시작번호

	private int endPage;// 화면 하단에 보여줄 페이지링크의 끝 번호


	// 왜 위에서 세 개만?? ㅇㅅㅇ
	public ArticlePage(List<ArticleFoodApply> artList, int currentPage, int total, int size, int blockSize) {

		// 여기서 size는 한 페이지에 보여줄 게시글의 갯수
		// blockSize는 한 페이지에서 보여줄 하단 페이지 링크 블럭 갯수

		this.artList = artList;
		this.currentPage = currentPage;
		this.total = total;

		// 전체 article의 갯수가 0일때
		if (total == 0) {
			totalPages = 0; // 페이지 갯수는 0으로처리
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size; // 이 경우 0 페이지가 될 수 있으니 그걸 막아주기 위해???

			if (total % size > 0) {
				totalPages++; // 1페이지 늘려줌.

				// 그리고 10일때는 1이고 다음페이지?
			}

			// [6][][][][]
			// 예를 들어 현재 페이지 넘버가 6이고 블록사이즈가 5라면 6/5 ->1 1*5 -> 5 5+1 ->6
			startPage = currentPage / blockSize * blockSize + 1;
			// 나누고 곱한다는건 나머지를 없애주는 작업을 하는 것.

			// 근데 만약 5페이지라면 나눴을때 0이 나와버림. 그래서 이런 식을 써준다.
			if ((currentPage % blockSize) == 0) {// 페이지 번호와 블럭 사이즈가 같아 페이지가 넘어가는 것을 막기 위함.
				startPage -= blockSize; // 빼줘버렸움 ㅇ ㅠㅇ
			}

			// [1][2][3][4][5]
			// 실질적으로 페이지가 3까지 있으면 4,5를 보여주면 안되므로 뒷부분을 잘라줘야 한다. 그러므로
			endPage = startPage + (blockSize - 1); // 다시
			if (endPage > totalPages) {
				endPage = totalPages; // end페이지와 total페이지를 같게 설정하면 뒷부분을 자를 수 있다.
			}

		}
	}
	
//	public boolean hasNoArticles() {
//
//		return total == 0;
//
//	}
	
	public boolean hasArticles() {
		return total > 0;
	}

	public List<ArticleFoodApply> getArtList() {
		return artList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotal() {
		return total;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

}
