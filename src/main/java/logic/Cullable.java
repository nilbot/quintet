package logic;

/**
 * Created by aaron on 15/04/16.
 */
public interface Cullable {
    GAData cull(CullConfig config, GAData input) throws Exception;
}
