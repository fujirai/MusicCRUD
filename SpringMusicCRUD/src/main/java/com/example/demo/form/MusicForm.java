package com.example.demo.form;

import lombok.Data;

@Data
public class MusicForm {
	private Integer song_id;
	private String song_name;
	private String singer;
	
	public MusicForm(Integer song_id,String song_name,String singer) {
		this.song_id=song_id;
		this.song_name=song_name;
		this.singer=singer;
	}
	//デフォルトコンストラクタ
	public MusicForm() {
		
	}
}