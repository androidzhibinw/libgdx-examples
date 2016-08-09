package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Color;


public class MyGdxGame extends ApplicationAdapter {
	static  float WORLD_WIDTH = 480;
	static  float WORLD_HEIGHT = 800;
    private Viewport viewport;
    private OrthographicCamera cam;
	SpriteBatch batch;
	Texture img;
    ShapeRenderer sp;

    @Override
	public void create () {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        Gdx.app.log("zhibinw","width:" + w + ",height:" +h );
        WORLD_HEIGHT = WORLD_WIDTH * h/w;
        cam = new OrthographicCamera(WORLD_WIDTH,WORLD_HEIGHT);
        cam.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        cam.update();
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,cam);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        sp = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.projection);
        //batch.setTransformMatrix(cam.view);
		batch.begin();
		//batch.draw(img, (WORLD_WIDTH - img.getWidth()) / 2, (WORLD_HEIGHT - img.getHeight()) / 2);
		batch.end();
        sp.setProjectionMatrix(cam.projection);
        sp.setTransformMatrix(cam.view);
        sp.begin(ShapeRenderer.ShapeType.Line);
        sp.setColor(Color.BLUE);
        sp.circle(WORLD_WIDTH/2,WORLD_HEIGHT/2,WORLD_WIDTH/2);
        sp.rect(10,10,WORLD_WIDTH/2,WORLD_WIDTH/2);
        sp.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
