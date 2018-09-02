
package android.databinding;
import com.khs.www.movietimechecker.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.khs.www.movietimechecker.R.layout.hotmovie_layout:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/hotmovie_layout_0".equals(tag)) {
                            return new com.khs.www.movietimechecker.databinding.HotmovieLayoutBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for hotmovie_layout is invalid. Received: " + tag);
                }
                case com.khs.www.movietimechecker.R.layout.theaterselectiontabboard_layout:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/theaterselectiontabboard_layout_0".equals(tag)) {
                            return new com.khs.www.movietimechecker.databinding.TheaterselectiontabboardLayoutBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for theaterselectiontabboard_layout is invalid. Received: " + tag);
                }
                case com.khs.www.movietimechecker.R.layout.favoritetheater_layout:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/favoritetheater_layout_0".equals(tag)) {
                            return new com.khs.www.movietimechecker.databinding.FavoritetheaterLayoutBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for favoritetheater_layout is invalid. Received: " + tag);
                }
                case com.khs.www.movietimechecker.R.layout.mainboard_layout:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/mainboard_layout_0".equals(tag)) {
                            return new com.khs.www.movietimechecker.databinding.MainboardLayoutBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for mainboard_layout is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 905068146: {
                if(tag.equals("layout/hotmovie_layout_0")) {
                    return com.khs.www.movietimechecker.R.layout.hotmovie_layout;
                }
                break;
            }
            case 456982409: {
                if(tag.equals("layout/theaterselectiontabboard_layout_0")) {
                    return com.khs.www.movietimechecker.R.layout.theaterselectiontabboard_layout;
                }
                break;
            }
            case -1524900438: {
                if(tag.equals("layout/favoritetheater_layout_0")) {
                    return com.khs.www.movietimechecker.R.layout.favoritetheater_layout;
                }
                break;
            }
            case -1032004974: {
                if(tag.equals("layout/mainboard_layout_0")) {
                    return com.khs.www.movietimechecker.R.layout.mainboard_layout;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"activity"
            ,"fragment"};
    }
}