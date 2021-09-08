package UI;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/8 16:22
 */
public class ShiPlaceHolder
{
    public float x;
    public float y;
    public Shi shi;
    public ShiPlaceHolder(float x,float y)
    {
        this.x = x;
        this.y = y;
    }
    public boolean isEmpty()
    {
        return shi==null;
    }

    public void addShi(Shi shi)
    {
        this.shi = shi;
        shi.des = this;
    }

}
