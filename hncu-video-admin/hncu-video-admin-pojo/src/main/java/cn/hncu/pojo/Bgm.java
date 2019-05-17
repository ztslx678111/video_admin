package cn.hncu.pojo;

public class Bgm {
	/**
	 * id
	 */
    private String id;
    /**
     * 作者 
     */
    private String author;
    /**
     * 歌名
     */
    private String name;
    /**
     * 路径
     */
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}