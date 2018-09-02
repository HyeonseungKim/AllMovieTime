package com.khs.www.movietimechecker.databinding;
import com.khs.www.movietimechecker.R;
import com.khs.www.movietimechecker.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FavoritetheaterLayoutBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.favoritetheaterlist, 1);
    }
    // views
    @NonNull
    public final android.support.v7.widget.RecyclerView favoritetheaterlist;
    @NonNull
    public final android.widget.LinearLayout mLinear;
    // variables
    @Nullable
    private com.khs.www.movietimechecker.FindTheaterRelated.FavoriteTheater mFragment;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FavoritetheaterLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.favoritetheaterlist = (android.support.v7.widget.RecyclerView) bindings[1];
        this.mLinear = (android.widget.LinearLayout) bindings[0];
        this.mLinear.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.fragment == variableId) {
            setFragment((com.khs.www.movietimechecker.FindTheaterRelated.FavoriteTheater) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFragment(@Nullable com.khs.www.movietimechecker.FindTheaterRelated.FavoriteTheater Fragment) {
        this.mFragment = Fragment;
    }
    @Nullable
    public com.khs.www.movietimechecker.FindTheaterRelated.FavoriteTheater getFragment() {
        return mFragment;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FavoritetheaterLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FavoritetheaterLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FavoritetheaterLayoutBinding>inflate(inflater, com.khs.www.movietimechecker.R.layout.favoritetheater_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FavoritetheaterLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FavoritetheaterLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.khs.www.movietimechecker.R.layout.favoritetheater_layout, null, false), bindingComponent);
    }
    @NonNull
    public static FavoritetheaterLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FavoritetheaterLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/favoritetheater_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FavoritetheaterLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): fragment
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}