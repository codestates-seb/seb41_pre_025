import { atom } from 'recoil';

export const loginState = atom({

  key: 'loginState',
  default: false,
});

export const userInfoState = atom({
  key: 'userInfoState',
  default: {},
});

export const questionListState = atom({
  key: 'questionListState',
  default: [],
});

export const questionDetailState = atom({
  key: 'questionDetailState',
  default: {},
});

export const answersState = atom({
  key: 'answersState',
  default: [],

});
