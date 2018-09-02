package com.khs.www.movietimechecker.databinding;
import com.khs.www.movietimechecker.R;
import com.khs.www.movietimechecker.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TheaterselectiontabboardLayoutBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.tabLayout, 2);
        sViewsWithIds.put(R.id.idforcoachmark, 3);
        sViewsWithIds.put(R.id.pager, 4);
        sViewsWithIds.put(R.id.fab, 5);
    }
    // views
    @NonNull
    public final android.support.design.widget.CoordinatorLayout coordinatorlayout;
    @NonNull
    public final android.support.design.widget.FloatingActionButton fab;
    @NonNull
    public final android.support.design.widget.TabItem idforcoachmark;
    @NonNull
    public final android.support.v4.view.ViewPager pager;
    @NonNull
    public final android.support.design.widget.TabLayout tabLayout;
    @NonNull
    public final android.support.v7.widget.Toolbar toolbar;
    // variables
    @Nullable
    private com.khs.www.movietimechecker.FindTheaterRelated.TheaterSelectionTabBoard mActivity;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public TheaterselectiontabboardLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.coordinatorlayout = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.coordinatorlayout.setTag(null);
        this.fab = (android.support.design.widget.FloatingActionButton) bindings[5];
        this.idforcoachmark = (android.support.design.widget.TabItem) bindings[3];
        this.pager = (android.support.v4.view.ViewPager) bindings[4];
        this.tabLayout = (android.support.design.widget.TabLayout) bindings[2];
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[1];
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
        if (BR.activity == variableId) {
            setActivity((com.khs.www.movietimechecker.FindTheaterRelated.TheaterSelectionTabBoard) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.khs.www.movietimechecker.FindTheaterRelated.TheaterSelectionTabBoard Activity) {
        this.mActivity = Activity;
    }
    @Nullable
    public com.khs.www.movietimechecker.FindTheaterRelated.TheaterSelectionTabBoard getActivity() {
        return mActivity;
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
    public static TheaterselectiontabboardLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static TheaterselectiontabboardLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<TheaterselectiontabboardLayoutBinding>inflate(inflater, com.khs.www.movietimechecker.R.layout.theaterselectiontabboard_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static TheaterselectiontabboardLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static TheaterselectiontabboardLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.khs.www.movietimechecker.R.layout.theaterselectiontabboard_layout, null, false), bindingComponent);
    }
    @NonNull
    public static TheaterselectiontabboardLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static TheaterselectiontabboardLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/theaterselectiontabboard_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new TheaterselectiontabboardLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): activity
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}