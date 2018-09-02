package com.khs.www.movietimechecker.databinding;
import com.khs.www.movietimechecker.R;
import com.khs.www.movietimechecker.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class MainboardLayoutBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appbarlayout, 6);
        sViewsWithIds.put(R.id.toolbar, 7);
        sViewsWithIds.put(R.id.mainlayout, 8);
        sViewsWithIds.put(R.id.whattext, 9);
        sViewsWithIds.put(R.id.whentext, 10);
        sViewsWithIds.put(R.id.wheretext, 11);
    }
    // views
    @NonNull
    public final android.support.design.widget.AppBarLayout appbarlayout;
    @NonNull
    public final android.widget.LinearLayout mainlayout;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final android.widget.Button search;
    @NonNull
    public final android.support.v7.widget.Toolbar toolbar;
    @NonNull
    public final android.widget.TextView toolbarTitle;
    @NonNull
    public final android.widget.TextView what;
    @NonNull
    public final android.widget.TextView whattext;
    @NonNull
    public final android.widget.TextView when;
    @NonNull
    public final android.widget.TextView whentext;
    @NonNull
    public final android.widget.TextView where;
    @NonNull
    public final android.widget.TextView wheretext;
    // variables
    @Nullable
    private com.khs.www.movietimechecker.MainBoardRelated.MainBoard mActivity;
    // values
    // listeners
    private OnClickListenerImpl mActivityOnWhereClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mActivityOnWhenClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mActivityOnSearchClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mActivityOnWhatClickedAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public MainboardLayoutBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.appbarlayout = (android.support.design.widget.AppBarLayout) bindings[6];
        this.mainlayout = (android.widget.LinearLayout) bindings[8];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.search = (android.widget.Button) bindings[5];
        this.search.setTag(null);
        this.toolbar = (android.support.v7.widget.Toolbar) bindings[7];
        this.toolbarTitle = (android.widget.TextView) bindings[1];
        this.toolbarTitle.setTag(null);
        this.what = (android.widget.TextView) bindings[2];
        this.what.setTag(null);
        this.whattext = (android.widget.TextView) bindings[9];
        this.when = (android.widget.TextView) bindings[3];
        this.when.setTag(null);
        this.whentext = (android.widget.TextView) bindings[10];
        this.where = (android.widget.TextView) bindings[4];
        this.where.setTag(null);
        this.wheretext = (android.widget.TextView) bindings[11];
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
            setActivity((com.khs.www.movietimechecker.MainBoardRelated.MainBoard) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.khs.www.movietimechecker.MainBoardRelated.MainBoard Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }
    @Nullable
    public com.khs.www.movietimechecker.MainBoardRelated.MainBoard getActivity() {
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
        com.khs.www.movietimechecker.MainBoardRelated.MainBoard activity = mActivity;
        android.view.View.OnClickListener activityOnWhereClickedAndroidViewViewOnClickListener = null;
        android.view.View.OnClickListener activityOnWhenClickedAndroidViewViewOnClickListener = null;
        android.view.View.OnClickListener activityOnSearchClickedAndroidViewViewOnClickListener = null;
        android.view.View.OnClickListener activityOnWhatClickedAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (activity != null) {
                    // read activity::onWhereClicked
                    activityOnWhereClickedAndroidViewViewOnClickListener = (((mActivityOnWhereClickedAndroidViewViewOnClickListener == null) ? (mActivityOnWhereClickedAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mActivityOnWhereClickedAndroidViewViewOnClickListener).setValue(activity));
                    // read activity::onWhenClicked
                    activityOnWhenClickedAndroidViewViewOnClickListener = (((mActivityOnWhenClickedAndroidViewViewOnClickListener == null) ? (mActivityOnWhenClickedAndroidViewViewOnClickListener = new OnClickListenerImpl1()) : mActivityOnWhenClickedAndroidViewViewOnClickListener).setValue(activity));
                    // read activity::onSearchClicked
                    activityOnSearchClickedAndroidViewViewOnClickListener = (((mActivityOnSearchClickedAndroidViewViewOnClickListener == null) ? (mActivityOnSearchClickedAndroidViewViewOnClickListener = new OnClickListenerImpl2()) : mActivityOnSearchClickedAndroidViewViewOnClickListener).setValue(activity));
                    // read activity::onWhatClicked
                    activityOnWhatClickedAndroidViewViewOnClickListener = (((mActivityOnWhatClickedAndroidViewViewOnClickListener == null) ? (mActivityOnWhatClickedAndroidViewViewOnClickListener = new OnClickListenerImpl3()) : mActivityOnWhatClickedAndroidViewViewOnClickListener).setValue(activity));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            com.khs.www.movietimechecker.MainBoardRelated.MainBoard.setFont(this.search, search.getResources().getString(R.string.font_bold));
            com.khs.www.movietimechecker.MainBoardRelated.MainBoard.setFont(this.toolbarTitle, toolbarTitle.getResources().getString(R.string.font_bold));
            com.khs.www.movietimechecker.MainBoardRelated.MainBoard.setFont(this.what, what.getResources().getString(R.string.font_light));
            com.khs.www.movietimechecker.MainBoardRelated.MainBoard.setFont(this.when, when.getResources().getString(R.string.font_light));
            com.khs.www.movietimechecker.MainBoardRelated.MainBoard.setFont(this.where, where.getResources().getString(R.string.font_light));
        }
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.search.setOnClickListener(activityOnSearchClickedAndroidViewViewOnClickListener);
            this.what.setOnClickListener(activityOnWhatClickedAndroidViewViewOnClickListener);
            this.when.setOnClickListener(activityOnWhenClickedAndroidViewViewOnClickListener);
            this.where.setOnClickListener(activityOnWhereClickedAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.khs.www.movietimechecker.MainBoardRelated.MainBoard value;
        public OnClickListenerImpl setValue(com.khs.www.movietimechecker.MainBoardRelated.MainBoard value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onWhereClicked(arg0);
        }
    }
    public static class OnClickListenerImpl1 implements android.view.View.OnClickListener{
        private com.khs.www.movietimechecker.MainBoardRelated.MainBoard value;
        public OnClickListenerImpl1 setValue(com.khs.www.movietimechecker.MainBoardRelated.MainBoard value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onWhenClicked(arg0);
        }
    }
    public static class OnClickListenerImpl2 implements android.view.View.OnClickListener{
        private com.khs.www.movietimechecker.MainBoardRelated.MainBoard value;
        public OnClickListenerImpl2 setValue(com.khs.www.movietimechecker.MainBoardRelated.MainBoard value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onSearchClicked(arg0);
        }
    }
    public static class OnClickListenerImpl3 implements android.view.View.OnClickListener{
        private com.khs.www.movietimechecker.MainBoardRelated.MainBoard value;
        public OnClickListenerImpl3 setValue(com.khs.www.movietimechecker.MainBoardRelated.MainBoard value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onWhatClicked(arg0);
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static MainboardLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static MainboardLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<MainboardLayoutBinding>inflate(inflater, com.khs.www.movietimechecker.R.layout.mainboard_layout, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static MainboardLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static MainboardLayoutBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.khs.www.movietimechecker.R.layout.mainboard_layout, null, false), bindingComponent);
    }
    @NonNull
    public static MainboardLayoutBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static MainboardLayoutBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/mainboard_layout_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new MainboardLayoutBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): activity
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}