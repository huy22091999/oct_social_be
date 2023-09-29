package vn.oceantech.mita.dto.search;

public class SearchDto {
    String keyWord;
    int pageIndex;
    int size;
    int status;

    SearchDto(){}

    public SearchDto(String keyWord, int pageIndex, int size,int status) {
        this.keyWord = keyWord;
        this.pageIndex = pageIndex;
        this.size = size;
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
