const MOCK_LAG = 10;

export function mockPromise<T>(func: () => any, mockLag: number = MOCK_LAG): Promise<T> {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      try {
        resolve(func());
      } catch (err) {
        reject(err);
      }
    }, mockLag !== undefined ? mockLag : MOCK_LAG)
  });
}