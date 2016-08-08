package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class MyGdxGame extends ApplicationAdapter {
	static final int WORLD_WIDTH = 100;
	static final int WORLD_HEIGHT = 100;
    private OrthographicCamera camera;
    Sprite mapSprite;
    float rotateSpeed;
    SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        rotateSpeed = 0.5f;
        mapSprite = new Sprite(new Texture(Gdx.files.internal("sc_map.png")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH,WORLD_HEIGHT);
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(30, 30*(width/height));
        camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0);
        camera.update();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
        handleInput();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
		//batch.draw(img, 0, 0);
        mapSprite.draw(batch);
		batch.end();
	}
	private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-3,0,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(3,0,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0,-3,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0,3,0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.rotate(-rotateSpeed,0,0,1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.rotate(rotateSpeed,0,0,1);
        }
        camera.zoom = MathUtils.clamp(camera.zoom,0.1f,100/camera.viewportWidth);
        float effectViewWidth = camera.viewportWidth * camera.zoom;
        float effectViewHeight = camera.viewportHeight * camera.zoom;

        camera.position.x = MathUtils.clamp(camera.position.x,effectViewWidth/2f, 100 - effectViewWidth /2f);
        camera.position.y = MathUtils.clamp(camera.position.y, effectViewHeight/2f, 100 - effectViewHeight/2f);
    }
    @Override
    public void resize (int width , int height) {
        camera.viewportWidth = 30f;
        camera.viewportHeight = 30 * height/width;
        camera.update();
    }
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
