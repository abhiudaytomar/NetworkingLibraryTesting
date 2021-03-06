
package com.abhiuday.networkinglibrarytesting.retrofit.model;

public class BlogInfo {
   	private boolean ask;
   	private boolean ask_anon;
   	private String ask_page_title;
   	private String description;
   	private boolean is_nsfw;
   	private String name;
   	private Number posts;
   	private boolean share_likes;
   	private String title;
   	private Number updated;
   	private String url;
    
    public BlogInfo() {}
 	public boolean getAsk(){
		return this.ask;
	}
	public void setAsk(boolean ask){
		this.ask = ask;
	}
 	public boolean getAsk_anon(){
		return this.ask_anon;
	}
	public void setAskAnon(boolean ask_anon){
		this.ask_anon = ask_anon;
	}
 	public String getAsk_page_title(){
		return this.ask_page_title;
	}
	public void setAskPageTitle(String ask_page_title){
		this.ask_page_title = ask_page_title;
	}
 	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
 	public boolean getIs_nsfw(){
		return this.is_nsfw;
	}
	public void setIsNsfw(boolean is_nsfw){
		this.is_nsfw = is_nsfw;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public Number getPosts(){
		return this.posts;
	}
	public void setPosts(Number posts){
		this.posts = posts;
	}
 	public boolean getShare_likes(){
		return this.share_likes;
	}
	public void setShareLikes(boolean share_likes){
		this.share_likes = share_likes;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public Number getUpdated(){
		return this.updated;
	}
	public void setUpdated(Number updated){
		this.updated = updated;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
}
