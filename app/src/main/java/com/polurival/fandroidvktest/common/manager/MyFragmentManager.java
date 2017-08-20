package com.polurival.fandroidvktest.common.manager;


import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

import com.polurival.fandroidvktest.ui.activity.BaseActivity;
import com.polurival.fandroidvktest.ui.fragment.BaseFragment;

import java.util.Stack;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

public class MyFragmentManager {

    private static final int EMPTY_FRAGMENT_STACK_SIZE = 1;

    private Stack<BaseFragment> mFragmentStack = new Stack<>();
    private BaseFragment mCurrentFragment;

    /*
     * устанавливает корневой фрагмент
     */
    public void setFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        if (activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)) {
            FragmentTransaction transaction = createAddTransaction(activity, fragment, false);
            transaction.replace(containerId, fragment);
            commitAddTransaction(activity, fragment, transaction, false);
        }
    }

    /**
     * добавляет фрагменты поверх корневого
     */
    public void addFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        if (activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)) {
            FragmentTransaction transaction = createAddTransaction(activity, fragment, true);
            transaction.add(containerId, fragment);
            commitAddTransaction(activity, fragment, transaction, true);
        }
    }

    private FragmentTransaction createAddTransaction(BaseActivity activity, BaseFragment fragment,
                                                     boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager()
                .beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        return fragmentTransaction;
    }

    private void commitAddTransaction(BaseActivity activity, BaseFragment fragment,
                                      FragmentTransaction transaction, boolean addToBackStack) {
        if (transaction != null) {
            mCurrentFragment = fragment;

            if (!addToBackStack) {
                mFragmentStack = new Stack<>();
            }

            mFragmentStack.add(fragment);

            commitTransaction(activity, transaction);
        }
    }

    public boolean removeCurrentFragment(BaseActivity activity) {
        return removeFragment(activity, mCurrentFragment);
    }

    public boolean removeFragment(BaseActivity activity, BaseFragment fragment) {
        boolean canRemove = fragment != null && mFragmentStack.size() > EMPTY_FRAGMENT_STACK_SIZE;

        if (canRemove) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

            mFragmentStack.pop();
            mCurrentFragment = mFragmentStack.lastElement();

            transaction.remove(fragment);
            commitTransaction(activity, transaction);
        }

        return canRemove;
    }

    /*
     * комит любых транзакций в не зависимости от того добавляются фрагменты или удаляются
     */
    private void commitTransaction(BaseActivity activity, FragmentTransaction transaction) {
        transaction.commit();

        activity.fragmentOnScreen(mCurrentFragment);
    }

    /*
     * проверяет, существует ли в стеке текущий фрагмент
     */
    public boolean isAlreadyContains(BaseFragment baseFragment) {
        if (baseFragment == null) {
            return false;
        }

        return mCurrentFragment != null
                && mCurrentFragment.getClass().getName().equals(baseFragment.getClass().getName());
    }
}
